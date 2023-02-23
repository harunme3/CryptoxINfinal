package com.cryptoxin.screenui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mr0xf00.lazytreelist.*


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ReferralPattern(navController: NavController){
    Node(nodeModel =  rootModel())

}

@ExperimentalAnimationApi
@Composable
fun Node(
    nodeModel: NodeModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        val isChildrenShown = remember { mutableStateOf(true) }

        NodeBox(
            modifier = Modifier.clickable(onClick = {
                isChildrenShown.value = !isChildrenShown.value
            }),
            isExpanded = isChildrenShown.value
        )

        Spacer(modifier = Modifier.size(8.dp))

        AnimatedVisibility(visible = isChildrenShown.value) {
            Row(
      horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(color = Color.Red)

            ) {
                nodeModel.children.forEachIndexed { index, model ->
                    Node(nodeModel = model)
                    if (index != nodeModel.children.size - 1) {
                        Spacer(modifier = Modifier.size(4.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun NodeBox(modifier: Modifier = Modifier, isExpanded: Boolean) {
    Box(
        modifier = modifier
            .size(8.dp)
            .background(color = if (isExpanded) Color.Green else Color.Blue)

    )
}



data class NodeModel(val children: List<NodeModel> = emptyList())

private fun rootModel(): NodeModel {
    return NodeModel(
        listOf(
            NodeModel(
                listOf(
                    NodeModel(
                        listOf(
                            NodeModel(
                                listOf(
                                    NodeModel(
                                        listOf(
                                            NodeModel(
                                                listOf(
                                                    NodeModel(
                                                        listOf(
                                                            NodeModel(),
                                                            NodeModel(),
                                                            NodeModel(),
                                                        )
                                                    ),
                                                    NodeModel(
                                                        listOf(
                                                            NodeModel(),
                                                            NodeModel(),
                                                            NodeModel(),
                                                        )
                                                    ),
                                                )
                                            ),
                                            NodeModel(
                                                listOf(
                                                    NodeModel(),
                                                    NodeModel(),
                                                    NodeModel(),
                                                )
                                            ),
                                        )
                                    ),
                                    NodeModel(
                                        listOf(
                                            NodeModel(),
                                            NodeModel(),
                                            NodeModel(),
                                        )
                                    ),
                                )
                            ),
                            NodeModel(
                                listOf(
                                    NodeModel(),
                                    NodeModel(),
                                    NodeModel(),
                                )
                            ),
                        )
                    ),
                    NodeModel(
                        listOf(
                            NodeModel(),
                            NodeModel(),
                            NodeModel(),
                        )
                    ),
                    NodeModel(
                        listOf(
                            NodeModel(),
                            NodeModel(),
                            NodeModel(),
                        )
                    )
                )
            ),

            NodeModel(
                listOf(
                    NodeModel(
                        listOf(
                            NodeModel(
                                listOf(
                                    NodeModel(
                                        listOf(
                                            NodeModel(
                                                listOf(
                                                    NodeModel(
                                                        listOf(
                                                            NodeModel(),
                                                            NodeModel(),
                                                            NodeModel(),
                                                        )
                                                    ),
                                                    NodeModel(
                                                        listOf(
                                                            NodeModel(),
                                                            NodeModel(),
                                                            NodeModel(),
                                                        )
                                                    ),
                                                )
                                            ),
                                            NodeModel(
                                                listOf(
                                                    NodeModel(),
                                                    NodeModel(),
                                                    NodeModel(),
                                                )
                                            ),
                                        )
                                    ),
                                    NodeModel(
                                        listOf(
                                            NodeModel(),
                                            NodeModel(),
                                            NodeModel(),
                                        )
                                    ),
                                )
                            ),
                            NodeModel(
                                listOf(
                                    NodeModel(),
                                    NodeModel(),
                                    NodeModel(),
                                )
                            ),
                        )
                    ),
                    NodeModel(
                        listOf(
                            NodeModel(),
                            NodeModel(),
                            NodeModel(),
                        )
                    ),
                    NodeModel(
                        listOf(
                            NodeModel(),
                            NodeModel(),
                            NodeModel(),
                        )
                    )
                )
            ),
        )
    )
}


