printjson(db.people.find(
    {
        birth_date: {
                        $gte: "2001-01-01", 
                        $lt: "2101-01-01"
                    }
    }, 
    {
        _id: 0, 
        first_name:1, 
        last_name:1, 
        "location.city": 1
    }
).toArray())
