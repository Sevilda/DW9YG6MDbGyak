db.auto.find({"$where":function(){
        if (this.ar < 700000 && this.gyartas_ev< 2010)
            return true;
        else
            return false;
    }
    })