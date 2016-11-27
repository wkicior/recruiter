#!/usr/bin/expect
set password [lindex $argv 0]
spawn /usr/local/glassfish4/bin/asadmin --user admin change-admin-password
expect "password"
send "\n"
expect "password"
send "$password\n"
expect "password"
send "$password\n"
expect eof
exit
