import org.junit.jupiter.api.Test

internal class Day5KtTest {

    @Test
    internal fun `should return segment for vertical line`() {
        val segmentPoints = toSegment(Point(0, 0), Point(0, 2))
        assert(segmentPoints.containsAll(listOf(Point(0, 0), Point(0, 1), Point(0, 2))))
    }

    @Test
    internal fun `should return segment for horizontal line`() {
        val segmentPoints = toSegment(Point(0, 0), Point(2, 0))
        assert(segmentPoints.containsAll(listOf(Point(2, 0), Point(1, 0), Point(0, 0))))
    }

    @Test
    internal fun `should return segment for diagonal line at 45 degrees`() {
        val segmentPoints = toSegment(Point(0, 0), Point(2, 2))
        assert(segmentPoints.containsAll(listOf(Point(0, 0), Point(1, 1), Point(2, 2))))
    }

    @Test
    internal fun `should return segment for diagonal line at 45 degrees reverted`() {
        val segmentPoints = toSegment(Point(2, 0), Point(0, 2))
        assert(segmentPoints.containsAll(listOf(Point(2, 0), Point(1,1),Point(0, 2))))
    }

    @Test
    internal fun `should return segment for diagonal line at 45 degrees not from origin`() {
        val segmentPoints = toSegment(Point(2, 3), Point(5, 6))
        assert(segmentPoints.containsAll(listOf(Point(2, 3), Point(3, 4), Point(4, 5), Point(5, 6))))
    }


}