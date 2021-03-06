import common.hadoop.extensions.split
import common.hadoop.extensions.toIntWritable
import common.hadoop.extensions.toText
import org.apache.hadoop.io.IntWritable
import org.apache.hadoop.io.LongWritable
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.Mapper
import java.util.regex.Pattern

/**
 * Represents the Mapper class used for this example.
 * The generic types correspond to <InputKey, InputValue, OutputKey, OutputValue>
 */
class MapperBigData : Mapper<LongWritable, Text, Text, IntWritable>() {
	private val one = 1.toIntWritable()

	override fun map(key: LongWritable, value: Text, context: Context) {
		// Splits each sentence in a list of words.
		val words = value.split(Pattern.compile("\\W+"))
		words.map(String::toLowerCase)
				.forEach { context.write(it.toText(), one) }
	}
}