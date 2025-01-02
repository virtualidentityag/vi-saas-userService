update CONSULTANT set TENANT_ID = '1';
update "session" set TENANT_ID = '1';
update "session" set TENANT_ID = '2' where ID in (1,2);
