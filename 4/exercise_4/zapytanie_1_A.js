printjson(db.people.aggregate(
[
 {
     $addFields: {
      numeric_weight: { $toDouble: "$weight" }
      }
  },
 {
     $group:
     {
         _id: "$sex",
         average_weight: {
             $avg: "$numeric_weight"
             }
      }
 }
]).toArray());

