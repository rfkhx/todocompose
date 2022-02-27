package top.ntutn.common.model

/**
 * Todo item的修改记录
 */
data class TodoRecordModel(
    private var id: Long = 0L,
    private var iid: String = "",
    private var operationTime: Long = 0L,
    private var operation: Int = 0
) {
    companion object {
        const val OPERATION_CREATE = 0
        const val OPERATION_MODIFY = 1
        const val OPERATION_DELETE = 2
    }
}