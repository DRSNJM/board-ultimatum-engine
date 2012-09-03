ORIGINAL_BRANCH=$(git branch 2> /dev/null | sed -e '/^[^*]/d' -e 's/* \(.*\)/\1/')
DOCS_BRANCH=gh-pages
SEPARATOR="============================================================================"

# Generate documentation using marginalia
docs:
	@echo
	@echo ${SEPARATOR}
	@echo "Using marginalia to create documentation. . ."
	@echo ${SEPARATOR}
	@-mkdir docs/
	lein marg -d docs/ -f index.html

# Copy generated docs into gh-pages branch
prepare_docs: docs
	@echo
	@echo ${SEPARATOR}
	@echo "Preparing marginalia docs in gh-pages branch. . ."
	@echo ${SEPARATOR}
	cp docs/* .git/_deploy/
	git checkout gh-pages
	cp .git/_deploy/* .
	-git commit -am "Updating documentation."
	@git checkout master > /dev/null

# Deploy prepared documents
deploy_docs: prepare_docs
	@echo
	@echo ${SEPARATOR}
	@echo "Attempting deployment to origin's ${DOCS_BRANCH} branch."
	@echo ${SEPARATOR}
	git push -u origin ${DOCS_BRANCH}:${DOCS_BRANCH}

# Should only be run once to make the gh-pages branch.
init_docs:
	@echo
	@echo ${SEPARATOR}
	@echo "Initializing orphan ${DOCS_BRANCH} branch. . ."
	@echo ${SEPARATOR}
	git checkout --orphan ${DOCS_BRANCH}
	g rm -rf .
	rm -rf docs target Makefile
	touch index.html
	git add index.html
	@echo
	@echo -e "\tAttempting an initial commit. . ."
	@echo
	git commit -am "Initial commit."
