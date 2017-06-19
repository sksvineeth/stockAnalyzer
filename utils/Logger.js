'use strict';

const winston = require('winston');
const tsFormat = () => (new Date()).toLocaleTimeString();
const logger = new (winston.Logger)({
  transports: [
    new (winston.transports.Console)({
      level: "silly",
      timestamp: tsFormat,
      colorize: true,
    })
  ]
});

module.exports = logger;
