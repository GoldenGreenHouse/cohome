INSERT INTO COHOME.USERCOMPONENT (ID, DTYPE, EMAIL, "NAME", PASSWORD, RUOLO, USERNAME) 
	VALUES (1, 'Moderatore', 'aaa@aaa.com', 'Ale', 'ale', 'Moderatore', 'Ale');
INSERT INTO COHOME.USERCOMPONENT (ID, DTYPE, EMAIL, "NAME", PASSWORD, RUOLO, USERNAME) 
	VALUES (2, 'Moderatore', 'eee@eee.com', 'Pippo', 'pippo', 'Moderatore', 'Pippo');
INSERT INTO COHOME.ANNUNCIO (ID, DTYPE, ATTIVO, DATAFINE, DATAINIZIO, DESCRIZIONE, LOCALITA, TITOLO, ATTRAZIONE, LUOGOINIZIALERITROVO, INDIRIZZO, LAT, LNG, NUMEROPOSTI) 
	VALUES (1, 'AnnuncioCasa', 0, '2014-04-02', '2014-04-01', 'Primo annuncio inserito', 'Torino', 'Primo', 'Stocazzo', 'Torino', 'Via pessinetto 8', 45.090071, 7.659426, 3);
INSERT INTO COHOME.USERCOMPONENT_ANNUNCIO (USERCOMPONENT_ID, ANNUNCI_ID) 
	VALUES (2, 1);
INSERT INTO COHOME.COMMENTO (ID, COMMENTO, AUTORE_ID) 
	VALUES (1, 'Tanta roba...', 1);
INSERT INTO COHOME.ANNUNCIO_COMMENTO (ANNUNCIO_ID, COMMENTI_ID) 
	VALUES (1, 1);


