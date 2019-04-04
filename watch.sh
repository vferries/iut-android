while inotifywait -e close_write *.asc --format %f; do asciidoc 13-kotlin.asc; done
