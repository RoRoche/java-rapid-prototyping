package fr.guddy.test;

import static guru.nidi.codeassert.pmd.PmdRulesets.basic;
import static guru.nidi.codeassert.pmd.PmdRulesets.braces;

import guru.nidi.codeassert.checkstyle.CheckstyleAnalyzer;
import guru.nidi.codeassert.checkstyle.CheckstyleResult;
import guru.nidi.codeassert.checkstyle.StyleChecks;
import guru.nidi.codeassert.checkstyle.StyleEventCollector;
import guru.nidi.codeassert.config.AnalyzerConfig;
import guru.nidi.codeassert.config.In;
import guru.nidi.codeassert.findbugs.BugCollector;
import guru.nidi.codeassert.findbugs.FindBugsAnalyzer;
import guru.nidi.codeassert.findbugs.FindBugsResult;
import guru.nidi.codeassert.junit.CodeAssertJunit5Test;
import guru.nidi.codeassert.pmd.PmdAnalyzer;
import guru.nidi.codeassert.pmd.PmdResult;
import guru.nidi.codeassert.pmd.PmdViolationCollector;

public final class CodeTest extends CodeAssertJunit5Test {

  private static final AnalyzerConfig CONFIG = AnalyzerConfig.gradle().main();

  @Override
  protected FindBugsResult analyzeFindBugs() {
    final BugCollector bugCollector = new BugCollector().just(
        In.classes("*Exception").ignore("SE_BAD_FIELD"));
    return new FindBugsAnalyzer(CONFIG, bugCollector).analyze();
  }

  @Override
  protected CheckstyleResult analyzeCheckstyle() {
    final StyleEventCollector bugCollector = new StyleEventCollector().just(
        In.everywhere().ignore("javadoc.missing", "hidden.field", "hide.utility.class", "javadoc.packageInfo"));
    return new CheckstyleAnalyzer(
        CONFIG,
        StyleChecks.sun(),
        bugCollector
    ).analyze();
  }

  @Override
  protected PmdResult analyzePmd() {
    final PmdViolationCollector collector = new PmdViolationCollector().just(
        In.everywhere().ignore("MethodArgumentCouldBeFinal"));
    return new PmdAnalyzer(CONFIG, collector).withRulesets(basic(), braces()).analyze();
  }
}