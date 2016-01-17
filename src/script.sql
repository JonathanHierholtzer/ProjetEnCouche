CREATE TABLE Produit(
	codeProduit NUMBER(10),
	nomProduit VARCHAR(20),
	prixProduit FLOAT(10),
	qteProduit NUMBER(10),
	CONSTRAINT pk_produit PRIMARY KEY (codeProduit),
	CONSTRAINT nn_qteProduit CHECK (qteProduit IS NOT NULL)
)

CREATE SEQUENCE seqProd
START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE PROCEDURE nouveauProduit (p_nomProduit IN Produit.nomProduit%TYPE,
p_prixProduit IN Produit.prixProduit%TYPE,
p_qteProduit IN Produit.qteProduit%TYPE) IS
BEGIN
INSERT INTO Produit (codeProduit, nomProduit, prixProduit, qteProduit)
VALUES (seqProd.NEXTVAL, p_nomProduit, p_prixProduit, p_qteProduit);

END;

