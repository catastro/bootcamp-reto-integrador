--crear la base de datos "bd-payment"

--inserts
db.service.insert(
    [
       {"description" : "Pago de Luz",
        "channel": "BI"
       },
       {"description" : "Pago de Luz",
        "channel": "BM"
       }
   ]
)