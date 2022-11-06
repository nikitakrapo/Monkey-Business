package com.nikitakrapo.component

import com.nikitakrapo.backpress.BackHandlerOwner
import com.nikitakrapo.lifecycle.LifecycleOwner

interface ComponentContext :
    LifecycleOwner,
    BackHandlerOwner