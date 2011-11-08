Objectif :

Comparer la serialisation/deserialisation XML et JSON de plusieurs librairies Java.

@see https://github.com/eishay/jvm-serializers/wiki/
@see http://blog.xebia.fr/2010/08/18/comparatif-des-librairies-json/

critères :
    type mapping (xsd code generation, annotation, mapping config file, java code, xpath, introspection...)
    mapping (primitifs, Date, Enum, List, Map...)
    serialization/deserialization
        constructeur
        getter/setter
        hierarchie
        required attribute, element
        optional (default value) attribute, element
        text element
        reference from id to ref in document
        xpath
    simplicité
    performance
    activité
    docs

librairies :
    xml-jaxb
        type
            +xsd code generation, annotation
        simplicité
            -pas simple, quelle implementation ?
            -
        stream-to-bean
        bean-to-stream
        mapping
            -n'aime pas les interfaces
        xsd-to-java
        performance
        activité
            2011
        docs
            il y a de la docs mais on ne trouve pas toujours ce que l'on veut en cas de problème
            http://thoughtforge.net/2010/02/04/marshalling-xml-with-spring-ws-and-jaxb/
            jaxb sans annotation : http://community.jboss.org/wiki/JAXBIntroductions

    xml-simple
        type
            +annotation, xpath, introspection
        simplicité
            +très simple
        stream-to-bean
        bean-to-stream
        mapping
            -le mapping de collection dont le type est abstrait implique l'ajout d'un attribut class="package.Class"
            +flexible, accepte l'instanciation par constructeur
        xsd-to-java
        performance
        activité
            +actif 2011
        docs
            +bonne document

    xml-jibx
        type
            stax, xsd, mapping config file
        simplicité
        stream-to-bean
        bean-to-stream
        mapping
            +non intrusif, byte code enhancement, flexible
        xsd-to-java
        performance
        activité
            +actif 2011
        docs
            de la doc, pas forcement facile à prendre en main
            probleme avec classe abstraite : http://jira.codehaus.org/browse/JIBX-385

    xml-xmlbeans
        type
            xsd code generation
        simplicité
        stream-to-bean
        bean-to-stream
        mapping
        xsd-to-java
        performance
        activité
        docs

    xml-xstream
        type
            annotation
        simplicité
        stream-to-bean
        bean-to-stream
        mapping
        xsd-to-java
        performance
        activité
        docs

    xml-castor
        type
            xsd code generation, mapping config file
        simplicité
        stream-to-bean
        bean-to-stream
        mapping
        xsd-to-java
        performance
        activité
            2011
        docs

    xml-jackson
    xml-protobuf
    xml-woodstox
    !xml-stax
        obsolete 2007
    !xml-javolution
        orienté real time
        il faut tout coder à la main

    json-gson
    json-jackson

    json-flexjson
    json-protobuf
    json-jsontools
    json-jsonlib
    json-jettison
    json-fastjson
    !json-json-org
    !json-sojo