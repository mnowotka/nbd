printjson(db.people.mapReduce(
  function() {
     emit(this.sex, {count: 1, total_weitht: parseFloat(this.weight)});    
  },
  function(key, values) {
    result = {count: 0, total_weitht: 0};
    for (value of values) {
        result.count += value.count;
        result.total_weitht += value.total_weitht;
    }
    return result;  
  },
  {
      out: {inline: 1},
      finalize: function(key, value) {
         return {
            sex: key,
            average_weight: value.total_weitht / value.count    
         }    
      }
  }
).results);
