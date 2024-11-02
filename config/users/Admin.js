db.createUser({
    user: 'Admin',
    pwd: 'Admin',
    roles: [{ role: 'userAdminAnyDatabase', db: 'admin' }]
});