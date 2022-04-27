db.system.js.save(
    {
    _id:"save_auto",
        value:function(tipus, szin, ar, gyev, allapot, tulaj ){
            db.auto.insert({"tipus": tipus, "szin": szin, "ar": ar, "gyartas_ev":gyev, "allapot": allapot, "tulaj": tulaj})
            }
        }
)
        
db.loadServerScripts();
save_auto("Kia", "piros", 500000, 2006, "jo", "T3")