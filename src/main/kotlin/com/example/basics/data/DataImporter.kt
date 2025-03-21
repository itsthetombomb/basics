//package com.example.basics.data
//
//import com.example.basics.model.MovieData
//import com.example.basics.repository.MovieRepository
//import com.opencsv.CSVReader
//import org.slf4j.LoggerFactory
//import org.springframework.boot.CommandLineRunner
//import org.springframework.stereotype.Component
//import java.io.InputStreamReader
//
//@Component
//class DataImporter(private val movieRepository: MovieRepository) : CommandLineRunner {
//    private val logger = LoggerFactory.getLogger(DataImporter::class.java)
//
//    override fun run(vararg args: String?) {
//        try {
//            val inputStream = javaClass.getResourceAsStream("/movie_data.csv")
//            if (inputStream == null) {
//                logger.error("Could not find movie_data.csv in resources")
//                return
//            }
//
//            InputStreamReader(inputStream).use { reader ->
//                CSVReader(reader).use { csvReader ->
//                    val header = csvReader.readNext()
//                    logger.info("CSV Header: ${header?.joinToString()}")
//
//                    var lineNumber = 1
//                    var nextLine = csvReader.readNext()
//                    var successCount = 0
//                    var errorCount = 0
//
//                    while (nextLine != null) {
//                        try {
//                            val movieData = MovieData(
//                                budget = nextLine[0],
//                                movieName = nextLine[2],
//                                overview = nextLine[4]
//                            )
//                            movieRepository.save(movieData)
//                            successCount++
//                        } catch (e: Exception) {
//                            logger.error("Error processing line $lineNumber: ${e.message}")
//                            logger.error("Line content: ${nextLine.joinToString()}")
//                            errorCount++
//                        }
//                        nextLine = csvReader.readNext()
//                        lineNumber++
//                    }
//
//                    logger.info("Import completed. Successfully imported: $successCount records. Failed: $errorCount records")
//                }
//            }
//        } catch (e: Exception) {
//            logger.error("Failed to import data", e)
//        }
//    }
//}
