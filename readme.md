**Task description**

Napiste vlakno, ktore cita z fronty povely a vykonava ich v poradi akom prisli do fronty. V pripade ze je fronta prazdna, vlakno bude cakat.

Povely su 3 typov: **Vloz**, **Vypis**, **ZmazVsetko**.

   - **Vloz** - *vlozi uzivatela do databazy*
   - **Vypis** - *vypise vsetkych uzivatelov na obrazovku (ak je databaza prazdna,
vypise oznam ze databaza je prazdna)*
   - **ZmazVsetko** - *zmaze vsetkych uzivatelov*

Uzivatel je definovany objektom, v databaze tabulkou **SUSERS**
a slpcami **USER_ID**, **USER_GUID**, **USER_NAME**

Navrhnite objekty, ktore budu reprezentovat povely a uzivatelov vo fronte
a spravte testovaciu metodu, ktora preveri funkcnost na tejto sekvencii:

```
Vloz (1, "a1", "Dusan")
Vloz (2, "a2", "Fero")
Vypis
ZmazVsetko
Vypis
```

Zadanie vypracujte ako kompilovatelny gradle project + unit testy