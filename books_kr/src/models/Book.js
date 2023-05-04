const { Model } = require('objection');
const Knex = require('knex');

const knex = Knex({
    client: 'postgresql',
    useNullAsDefault: true,
    connection: {
      
    }
  });