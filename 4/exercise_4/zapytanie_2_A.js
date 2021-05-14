printjson(db.people.aggregate([
    {
        $unwind : "$credit"
    },
    {
        $addFields: {
            "credit.numeric_balance": { $toDecimal: "$credit.balance" }
        }
    },
    {
        $group:
        {
           _id: "$credit.currency",
           total_balance: {
             $sum: "$credit.numeric_balance"
           }
        }
    },
    { $sort : { _id : 1 } }
]).toArray());
