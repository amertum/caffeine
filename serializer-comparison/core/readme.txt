Objectif :

Comparer la serialisation/deserialisation XML et JSON de plusieurs librairies Java.

critères :
    type (xsd, annotation, mapping config file, java code, xpath, introspection...)
    simplicité
    stream-to-bean
    bean-to-stream
    mapping (primitifs, Date, Enum, List, Map...)
    xsd-to-java
    performance
    activité
    docs

librairies :
    xml-jaxb
        type
            +xsd code generation, annotation
        simplicité
            -pas simple, quelle implementation ? difficile de faire sans XSD
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

    xml-simple
        type
            +annotation, xpath, introspection
        simplicité
            +très simple
        stream-to-bean
        bean-to-stream
        mapping
            -le mapping de collection dont le type est abstrait implique l'ajout d'un attribut class="package.Class"
        xsd-to-java
        performance
        activité
            +actif 2011
        docs
            +bonne document

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

    xml-jibx
        type
            stax, xsd, mapping config file
        simplicité
        stream-to-bean
        bean-to-stream
        mapping
        xsd-to-java
        performance
        activité
        docs

    xml-jackson
    xml-castor
    xml-protobuf
    xml-woodstox
    !xml-stax
        obsolete 2007
    !xml-javolution
        orienté real time
        il faut tout coder à la main

    json-gson

    json-json-org
    json-flexjson
    json-jackson
    json-protobuf
    json-jsontools
    json-jsonlib
    json-jettison
    json-sojo