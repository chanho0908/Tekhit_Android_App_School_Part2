package kr.co.lion.android29_message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.android29_message.databinding.FragmentRequestBinding

class RequestFragment : Fragment() {
    private lateinit var binding: FragmentRequestBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentRequestBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSendData.setOnClickListener {
            val data = "A" // 전달할 데이터
            val intent = (activity as? ReqeustActivity)?.createIntentWithData(data)
            startActivity(intent)
        }
    }
}