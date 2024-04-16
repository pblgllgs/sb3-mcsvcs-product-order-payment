
stop_db:
	@echo "Deteniendo el servicio Oracle..."
	@powershell -Command "Start-Process cmd -ArgumentList '/c sc stop OracleServiceXE' -Verb RunAs"
	@echo "Servicio detenido correctamente."

	@echo "Deteniendo el servicio Oracle Listener..."
	@powershell -Command "Start-Process cmd -ArgumentList '/c sc stop OracleOraDB21Home1TNSListener' -Verb RunAs"
	@echo "Servicio detenido correctamente."

start_db:
	@echo "Iniciando el servicio OracleXE..."
	@powershell -Command "Start-Process cmd -ArgumentList '/c sc start OracleOraDB21Home1TNSListener' -Verb RunAs"
	@echo "Servicio iniciado correctamente."

	@echo "Iniciando el servicio Oracle Listener..."
	@powershell -Command "Start-Process cmd -ArgumentList '/c sc start OracleServiceXE' -Verb RunAs"
	@echo "Servicio iniciado correctamente."