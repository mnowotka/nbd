printjson(db.people.mapReduce(
   function() {
       for (card of this.credit) {
           emit(card.currency, {total_balance: parseFloat(card.balance)});
       }
   },
   function(key, values) {
       var ret = {total_balance: 0};
       for (value of values) {
           ret.total_balance += value.total_balance;
       }
       return ret;
   },
     {
      out: {inline: 1},
      finalize: function(key, value) {
         return {
            currency: key,
            total_balance: value.total_balance
         }
      }
  }
).results);
