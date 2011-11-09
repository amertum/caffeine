Objectif :

Comparer la serialisation/deserialisation XML et JSON de plusieurs librairies Java.

@see https://github.com/eishay/jvm-serializers/wiki/
@see http://blog.xebia.fr/2010/08/18/comparatif-des-librairies-json/
@see http://code.google.com/p/json-smart/wiki/Benchmark

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
        rapide et simple à utiliser
    json-flexjson
    
    json-protobuf
        pas vraiment flexible sur la generation XML et JSON, ce n'est pas fait pour ça mais plutot pour de la communication server-server.
        mais c'est hyper performant
    json-jsontools
    json-jsonlib
    json-jettison
    json-fastjson
    !json-json-org
    !json-sojo

Some Results :

http://chart.apis.google.com/chart?chs=300x450&cht=bhg&chbh=10,5,10&chco=4D89D9|8D89D9|BD89D9&chxt=y,y,x&chtt=basic+fields+mean&chds=a&chxl=0:|Gson-10|Gson-100|Gson-1000|XStream-10|XStream-100|XStream-1000|Jackson-10|Jackson-100|Jackson-1000|Jaxb-10|Jaxb-100|Jaxb-1000|Jibx-10|Jibx-100|Jibx-1000|Simple-10|Simple-100|Simple-1000|1:|0.85+ms|0.71+ms|0.43+ms|7.42+ms|4.18+ms|2.35+ms|0.20+ms|0.20+ms|0.19+ms|3.78+ms|4.19+ms|2.44+ms|0.27+ms|0.20+ms|0.21+ms|2.12+ms|1.83+ms|1.19+ms|2:|0|1|2|3|4|5|6|7|8&chd=t:2.12,1.83,1.19,0.27,0.20,0.21,3.78,4.19,2.44,0.20,0.20,0.19,7.42,4.18,2.35,0.85,0.71,0.43&.png

Result: Simon Stopwatch: total 122 ms, counter 1, max 122 ms, min 122 ms, mean 122 ms [XStream-1 INHERIT]
Result: Simon Stopwatch: total 74.2 ms, counter 10, max 14.0 ms, min 5.74 ms, mean 7.42 ms [XStream-10 INHERIT]
Result: Simon Stopwatch: total 418 ms, counter 100, max 7.72 ms, min 3.19 ms, mean 4.18 ms [XStream-100 INHERIT]
Result: Simon Stopwatch: total 2.35 s, counter 1000, max 5.96 ms, min 1.78 ms, mean 2.35 ms [XStream-1000 INHERIT]

Result: Simon Stopwatch: total 57.0 ms, counter 1, max 57.0 ms, min 57.0 ms, mean 57.0 ms [Jaxb-1 INHERIT]
Result: Simon Stopwatch: total 37.8 ms, counter 10, max 4.41 ms, min 3.27 ms, mean 3.78 ms [Jaxb-10 INHERIT]
Result: Simon Stopwatch: total 419 ms, counter 100, max 7.36 ms, min 2.94 ms, mean 4.19 ms [Jaxb-100 INHERIT]
Result: Simon Stopwatch: total 2.44 s, counter 1000, max 5.25 ms, min 1.84 ms, mean 2.44 ms [Jaxb-1000 INHERIT]

Result: Simon Stopwatch: total 73.4 ms, counter 1, max 73.4 ms, min 73.4 ms, mean 73.4 ms [Simple-1 INHERIT]
Result: Simon Stopwatch: total 21.2 ms, counter 10, max 2.68 ms, min 1.76 ms, mean 2.12 ms [Simple-10 INHERIT]
Result: Simon Stopwatch: total 183 ms, counter 100, max 2.73 ms, min 1.38 ms, mean 1.83 ms [Simple-100 INHERIT]
Result: Simon Stopwatch: total 1.19 s, counter 1000, max 10.2 ms, min 900 us, mean 1.19 ms [Simple-1000 INHERIT]

Result: Simon Stopwatch: total 108 ms, counter 1, max 108 ms, min 108 ms, mean 108 ms [Gson-1 INHERIT]
Result: Simon Stopwatch: total 8.55 ms, counter 10, max 929 us, min 829 us, mean 855 us [Gson-10 INHERIT]
Result: Simon Stopwatch: total 71.5 ms, counter 100, max 1.32 ms, min 566 us, mean 715 us [Gson-100 INHERIT]
Result: Simon Stopwatch: total 432 ms, counter 1000, max 3.29 ms, min 302 us, mean 432 us [Gson-1000 INHERIT]

Result: Simon Stopwatch: total 20.2 ms, counter 1, max 20.2 ms, min 20.2 ms, mean 20.2 ms [Jibx-1 INHERIT]
Result: Simon Stopwatch: total 2.72 ms, counter 10, max 589 us, min 206 us, mean 272 us [Jibx-10 INHERIT]
Result: Simon Stopwatch: total 20.2 ms, counter 100, max 266 us, min 197 us, mean 202 us [Jibx-100 INHERIT]
Result: Simon Stopwatch: total 212 ms, counter 1000, max 2.40 ms, min 173 us, mean 212 us [Jibx-1000 INHERIT]

Result: Simon Stopwatch: total 56.8 ms, counter 1, max 56.8 ms, min 56.8 ms, mean 56.8 ms [Jackson-1 INHERIT]
Result: Simon Stopwatch: total 1.98 ms, counter 10, max 342 us, min 181 us, mean 198 us [Jackson-10 INHERIT]
Result: Simon Stopwatch: total 19.7 ms, counter 100, max 1.78 ms, min 167 us, mean 197 us [Jackson-100 INHERIT]
Result: Simon Stopwatch: total 188 ms, counter 1000, max 4.99 ms, min 159 us, mean 188 us [Jackson-1000 INHERIT]
