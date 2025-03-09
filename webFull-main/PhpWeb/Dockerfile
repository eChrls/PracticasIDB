FROM php:8.4-apache

COPY docker-php.conf /etc/apache2/conf-avaliable/docker-php.conf

COPY . /var/www/html/

RUN docker-php-ext-install mysqli pdo_mysql

RUN a2enmod rewrite