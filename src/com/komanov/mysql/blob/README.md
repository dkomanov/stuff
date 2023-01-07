### MySQL Blob Throughput

Tests/benchmarks for getting large blobs from MySQL.

#### Install MySQL

```bash
sudo apt install mysql-server-8.0
sudo cat /etc/mysql/debian.cnf # take user/password from it
```

#### Init Schema

```sql
CREATE DATABASE blob_db;
USE blob_db;

CREATE TABLE uncompressed_blobs (
  id INT NOT NULL PRIMARY KEY,
  data MEDIUMBLOB NOT NULL
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

CREATE TABLE compressed_blobs (
  id INT NOT NULL PRIMARY KEY,
  data MEDIUMBLOB NOT NULL
) ENGINE=InnoDB ROW_FORMAT=COMPRESSED;
```

* https://dev.mysql.com/doc/refman/8.0/en/innodb-row-format.html

#### Run Benchmark

* Comment out jdk8/jdk11 (irrelevant for the benchmark)
* `scripts/run-jmh.sh //src/com/komanov/mysql/blob/jmh:jmh mysql-blob-fetch`
