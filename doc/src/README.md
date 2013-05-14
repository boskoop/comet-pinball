Setup instructions
==================

The .tex-files use the gitinfo package. In order to setup gitinfo you have to create the following hooks in your git repo and make them executable:

post-checkout
post-commit
post-merge

Place those files in the <repodir>/.git/hooks directory with the following content:

#!/bin/sh
# Copyright 2011 Brent Longborough
# Please read gitinfo.pdf for licencing and other details
# -----------------------------------------------------
# Post-{commit,checkout,merge} hook for the gitinfo package
#
prefixes="doc/src"    # Example for multiple gitHeadInfo.tex files
#prefixes="."              # Default --- in the working copy root
for pref in $prefixes
        do
        git log -1 --date=short \
        --pretty=format:"\usepackage[%
                shash={%h},
                lhash={%H},
                authname={%an},
                authemail={%ae},
                authsdate={%ad},
                authidate={%ai},
                authudate={%at},
                commname={%an},
                commemail={%ae},
                commsdate={%ad},
                commidate={%ai},
                commudate={%at},
                refnames={%d}
        ]{gitsetinfo}" HEAD > $pref/gitHeadInfo.gin
        done
# EOF
