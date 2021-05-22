printjson(db.people.mapReduce(
  function() {
     emit(this.sex, {count: 1, total_weight: parseFloat(this.weight)});
  },
  function(key, values) {
    result = {count: 0, total_weight: 0};
    for (value of values) {
        result.count += value.count;
        result.total_weight += value.total_weight;
    }
    return result;  
  },
  {
      out: {inline: 1},
      finalize: function(key, value) {
         return {
            sex: key,
            average_weight: value.total_weight / value.count
         }    
      }
  }
).results);
