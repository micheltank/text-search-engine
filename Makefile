 docker_build:
	docker build -t text-search-engine .
 docker_run:
	@echo "---- Running Docker Image ----"
	@docker run -it --rm  -v "$(pwd)"/example:/app/files text-search-engine
 run_local:
	java -jar app.jar example