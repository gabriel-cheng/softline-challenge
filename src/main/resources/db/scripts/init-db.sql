IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'softlineChallenge')
BEGIN
    CREATE DATABASE softlineChallenge;
END
GO