node {
  checkout scm
  jobDsl targets: ['dsl/builder.groovy'].join('\n'),
       removedJobAction: 'IGNORE',
       removedViewAction: 'IGNORE',
       lookupStrategy: 'SEED_JOB'
}