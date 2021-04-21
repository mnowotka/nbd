printjson(db.people.find(
    {
        weight: {
                    $gte: "68.14", 
                    $lt: "71.5"
                }
    }
).toArray())
