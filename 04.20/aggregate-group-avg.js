db.auto.aggregate( [ 
    {"$group": {"_id": "$tipus", "avgPrice": {"$avg":"$ar"}}},
    {"$sort": {"avgPrice":-1}}
])