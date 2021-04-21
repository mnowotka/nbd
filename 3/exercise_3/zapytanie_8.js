printjson(
    db.getCollection('people').updateMany(
        {
            "location.city": "Moscow"
        },
        {
            $set:
               {
                   "location.city": "Moskwa"
               }
        }
    )
)
