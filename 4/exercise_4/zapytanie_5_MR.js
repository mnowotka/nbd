printjson(db.people.mapReduce(
   function() {
       if (this.sex !== "Female" || this.nationality !== "Poland") {
           return;
       }
       for (card of this.credit) {
           emit(card.currency, {total_balance: parseFloat(card.balance), count: 1});
       }
   },
   function(key, values) {
       var ret = {total_balance: 0, count: 0};
       for (value of values) {
           ret.total_balance += value.total_balance;
           ret.count += 1;
       }
       return ret;
   },
     {
      out: {inline: 1},
      finalize: function(key, value) {
         return {
            currency: key,
            total_balance: value.total_balance,
            average_balance: value.total_balance / value.count
         }
      }
  }
).results);
