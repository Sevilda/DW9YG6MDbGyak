db.system.js.save(
    {
    _id:"getTulajByName",
        value:function(nev){
            var n =db.Tulajdonos.find({"nev": nev})
            while(n.hasNext()) {
                return( n.next()._id)}
            }
        }
)
        
db.loadServerScripts();
getTulajByName("Pal");