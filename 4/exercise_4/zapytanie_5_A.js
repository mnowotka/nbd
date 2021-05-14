printjson(db.people.aggregate(
[
    {$match:
        {sex: "Female", nationality: "Poland"}
    },
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
           },
           average_balance: {
             $avg: "$credit.numeric_balance"
           }
        }
    },
    { $sort : { _id : 1 } }
]
).toArray())
