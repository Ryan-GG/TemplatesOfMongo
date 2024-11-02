db.createUser({
     user: 'ReadpnlyUser',
     pwd: 'ReadpnlyUser',
     roles: [{ role: 'read', db: 'admin' }]
 });