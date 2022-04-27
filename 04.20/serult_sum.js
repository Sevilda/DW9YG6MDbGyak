db.auto.aggregate( [ 
    {"$match": {"allapot":"serult"}},
    {"$group": {"_id": "$tipus", "serult": {"$sum":1}}},
    {"$sort": {"serult":-1}}
])