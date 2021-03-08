print('Start #################################################################');

db = db.getSiblingDB('iupp_mongo_db');
db.createUser(
    {
      user: 'iupp_mongo',
      pwd: 'iupp_mongo',
      roles: [{ role: 'readWrite', db: 'iupp_mongo_db' }],
    },
);
db.createCollection('teste');

print('END #################################################################');
