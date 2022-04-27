db.system.js.save(
    {
    _id:"save_tulaj",
        value:function(id, nev, kor){
            db.Tulajdonos.insert({"_id": id, "nev": nev, "kor": kor})
            }
        }
)
        
db.loadServerScripts();
save_tulaj("T2", "Valaki", 43);