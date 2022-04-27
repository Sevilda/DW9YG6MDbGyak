db.auto.find({"allapot":"serult"}).forEach(
    function(obj){
        db.auto.update({"_id": obj._id},{"$inc": {"ar": -30000}})
    }
)
   