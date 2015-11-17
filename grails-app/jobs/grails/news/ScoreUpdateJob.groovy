package grails.news

class ScoreUpdateJob {
	def scoringService
    static triggers = {
      simple repeatInterval: 60l*60000l // execute job once in 5 seconds
    }

    def execute() {
    	scoringService.refreshAllScores()
        // execute job
    }
}
