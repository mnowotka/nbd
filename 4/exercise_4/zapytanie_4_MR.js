printjson(db.people.mapReduce(
  function() {
     var bmi = parseFloat(this.weight) / Math.pow((parseFloat(this.height) / 100.0), 2);
     emit(this.nationality, {min_bmi: bmi, max_bmi: bmi, total_bmi: bmi, count: 1});
  },
  function(key, values) {
    var first_value = values[0];
    result = {min_bmi: first_value.min_bmi, max_bmi: first_value.max_bmi, total_bmi: 0, count: 0};
    for (value of values) {
        result.count += value.count;
        result.total_bmi += value.total_bmi;
        result.min_bmi = Math.min(result.min_bmi, value.min_bmi);
        result.max_bmi = Math.max(result.max_bmi, value.max_bmi);
    }
    return result;
  },
  {
      out: {inline: 1},
      finalize: function(key, value) {
         return {
            nationality: key,
            min_bmi: value.min_bmi,
            max_bmi: value.max_bmi,
            avr_bmi: value.total_bmi / value.count
         }
      }
  }
).results);
