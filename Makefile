

STYLE:=src/main/resources/xslt/cars-template.xsl
DATA:=src/main/resources/data/cars.xml
OUTPUT:=cars.pdf

build:
	mvn package

run:
	mvn exec:java -Dexec.mainClass=com.gitrust.fopexample.App -Dexec.args='-s $(STYLE) -d $(DATA) -o $(OUTPUT)'
