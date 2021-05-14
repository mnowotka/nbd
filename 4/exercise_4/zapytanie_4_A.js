printjson(db.people.aggregate(
[
 {
     $addFields: {
      numeric_weight: { $toDouble: "$weight" },
      numeric_height: {$divide: [{$toDouble: "$height"}, 100]},
      }
  },
 {
     $addFields: {
        bmi: {$divide: ["$numeric_weight", {$pow: ["$numeric_height", 2]}]}
     }
 },
 {
     $group:
     {
         _id: "$nationality",
         min_bmi: {$min: "$bmi"},
         max_bmi: {$max: "$bmi"} ,
         avg_bmi: {$avg: "$bmi"} 
     } 
 },
 {
     $sort: {_id: 1}
 }
]).toArray());
