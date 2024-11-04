db.createUser({
     user: 'ReadonlyUser',
     pwd: 'ReadonlyUser',
     roles: [{ role: 'read', db: 'admin' }]
 });