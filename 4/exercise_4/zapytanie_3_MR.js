printjson(db.people.mapReduce(
    function() {
      emit(this.job, null);
    },
    function() {return null;},
    {
      out: {inline: 1}
  }
).results);

