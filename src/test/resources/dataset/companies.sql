DELETE FROM company WHERE document = 'first_document';
DELETE FROM company WHERE document = 'second_document';
DELETE FROM company WHERE document = 'document';


INSERT INTO company (document, fantasy_name, company_name)
VALUES ( 'first_document', 'First Fantasy Name', 'First Company Name');

INSERT INTO company (document, fantasy_name, company_name)
VALUES ( 'second_document', 'Second Fantasy Name', 'Second Company Name');

INSERT INTO company (document, fantasy_name, company_name)
VALUES ( 'document', 'Fantasy Name', 'Company Name');
