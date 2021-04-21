printjson(
    db.people.insertOne(
{
        "sex" : "Male",
        "first_name" : "Michal",
        "last_name" : "Nowotka",
        "job" : "Student",
        "email" : "s23455@pjwstk.edu.pl",
        "location" : {
            "city" : "Warsaw",
            "address" : {
                "streetname" : "Koszykowa",
                "streetnumber" : "65"
            }
        },
        "description" : "in quam fringilla rhoncus mauris enim leo rhoncus sed vestibulum sit amet cursus",
        "height" : "167.54",
        "weight" : "68.7",
        "birth_date" : "1986-01-24T12:20:27Z",
        "nationality" : "Poland",
        "credit" : [
            {
                "type" : "jcb",
                "number" : "6331102785087918",
                "currency" : "PLN",
                "balance" : "2957.97"
            }
        ]
}
))
