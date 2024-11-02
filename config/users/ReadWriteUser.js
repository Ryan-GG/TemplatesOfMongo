db.createUser({
    user: 'ReadWriteUser',
    pwd: 'ReadWriteUser',
    roles: [{ role: 'readWrite', db: 'admin' }]
});